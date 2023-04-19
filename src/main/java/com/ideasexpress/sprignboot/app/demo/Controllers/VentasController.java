package com.ideasexpress.sprignboot.app.demo.Controllers;



import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ideasexpress.sprignboot.app.demo.Models.DAO.IClienteDao;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.IDetalleDao;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.IProductoDao;
import com.ideasexpress.sprignboot.app.demo.Models.DAO.IVentasDao;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Detalle;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Producto;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.ProductoListo;
import com.ideasexpress.sprignboot.app.demo.Models.Entity.Ventas;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IDetalleRepo;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IProductoNuevo;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IProductoRepo;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IVentaRepo;
import com.ideasexpress.sprignboot.app.demo.Service.Insercion;

import io.micrometer.core.ipc.http.HttpSender.Request;

@Repository
@SessionAttributes("ventas")
@RequestMapping("/ventas")
public class VentasController {
    @Autowired
    private IVentasDao ventasDao;
    @Autowired
    private IDetalleDao detalleDao;
    @Autowired
    private IProductoDao productoDao;
    @Autowired
    private IClienteDao clienteDao;
    @Autowired
    private Insercion insercion;
    @Autowired
    private IVentaRepo ventaRepo;
    @Autowired
    private IProductoRepo productoRepo;
    @Autowired
    private IProductoNuevo productoNuevo;
    @Autowired
    private IDetalleRepo detalleRepo;

    private Cliente cliente;
    @GetMapping("/listar/{id}")
    public String listar(@PathVariable(value="id")Long id,Model model){
        //ya sabemos que cliente es

        
        ventasDao.subTotalList(ventasDao.findOneCliente(id), detalleDao.findVentas(id));
        
        model.addAttribute("tituloP", "Ventas");
        model.addAttribute("ventas", ventasDao.findOneCliente(id));
        model.addAttribute("detalles", detalleDao.findVentas(id));
        model.addAttribute("total", ventasDao.totalFactura(ventasDao.findOneCliente(id)));

        return"/ventas/listar";
    }

    @GetMapping("/form/{id}") //Se ejecuta como caso de uso desde la web
    public String crear(@PathVariable(value="id")Long id,Model model, HttpServletRequest request){
        Ventas venta = new Ventas();
        cliente = clienteDao.findOne(id);
        System.out.println(cliente);
        model.addAttribute("titulo", "Nueva venta");
        model.addAttribute("valor", "Crear venta");

        model.addAttribute("cliente", clienteDao.findOne(id));
        model.addAttribute("productos", productoDao.findAll());
        model.addAttribute("detalle", new Detalle());
        model.addAttribute("ventas", venta);

        int tot = 0;
        ArrayList<Detalle> canasta = this.getCanasta(request);
        for (Detalle d:canasta) tot +=d.getValorVenta()*d.getCantidad();
        model.addAttribute("total",tot);
        return "ventas/form";
    }

    private ArrayList<Detalle> getCanasta(HttpServletRequest request){
        ArrayList<Detalle> canasta = (ArrayList<Detalle>) request.getSession().getAttribute("canasta");

        if(canasta ==null){
            canasta = new ArrayList<>();
        }

        return canasta;
    }

    private void guardarCanasta(ArrayList<Detalle> canasta, HttpServletRequest request){
        request.getSession().setAttribute("canasta", canasta);
    }


    @PostMapping(value="/añadir")
    public String canasta(@ModelAttribute Producto producto,HttpServletRequest request, RedirectAttributes redirectAttrs){
        //insercion.ventasPorCliente();
        ArrayList<Detalle> canasta = this.getCanasta(request);
        
        Producto productoConId = productoRepo.findByid(producto.getId());
        if (productoConId== null)
        {
            redirectAttrs
                .addFlashAttribute("mensaje", "El producto con el código " + producto.getId() + " no existe")
                .addFlashAttribute("clase", "warning");
        
                return"redirect:/ventas/form/"+cliente.getId();
        }
        
        if (productoConId.sinExistencia())
        {
            redirectAttrs
                .addFlashAttribute("mensaje", "El producto está agotado")
                .addFlashAttribute("clase", "warning");
            
                return"redirect:/ventas/form/"+cliente.getId();
        }

        boolean Encontrado = false;

        for (Detalle productoParaVenderActual: canasta)
        {
            if (productoParaVenderActual.getId().equals(productoConId.getId()))
            {
                productoParaVenderActual.aumentarCantidad();
                Encontrado = true;
                
                break;
            }
        }
    
        if (!Encontrado)
        {
            canasta.add(new Detalle(productoConId.getId(),1, productoConId.getPrecio(), productoConId));
        }
    
        this.guardarCanasta(canasta, request);
        return"redirect:/ventas/form/"+cliente.getId();
    }
    
    @PostMapping(value = "/Quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request)
    {
        ArrayList<Detalle> canasta = this.getCanasta(request);
    
        if (canasta != null && canasta.size() > 0 && canasta.get(indice) != null)
        {
            canasta.remove(indice);
            this.guardarCanasta(canasta, request);
        }
        System.out.println(canasta);
        return"redirect:/ventas/form/"+cliente.getId();
    }

    private void limpiarCanasta(HttpServletRequest request)
    {
        this.guardarCanasta(new ArrayList<>(), request);
    }
    
    @GetMapping(value = "/limpiar")

    public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs)
    {
        this.limpiarCanasta(request);
        
        redirectAttrs
                .addFlashAttribute("mensaje", "Venta cancelada")
                .addFlashAttribute("clase", "info");

                return"redirect:/ventas/form/"+cliente.getId();
    }

    @PostMapping(value = "/finalizar")
    
    public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) 
    {
        System.out.println("SI ENTRA");
        ArrayList<Detalle> canasta = this.getCanasta(request);
        Detalle det = new Detalle();
        // Si no hay carrito o está vacío, regresamos inmediatamente

        if (canasta == null || canasta.size() <= 0) {
            return"redirect:/ventas/form/"+cliente.getId();
        }

        Ventas v = ventaRepo.save(new Ventas());
        //v.setCliente(cliente);
        
        // Recorrer el carrito


        for (Detalle productoParaVender : canasta)
        {
            // Obtener el producto fresco desde la base de datos

            Producto p = productoDao.findOne(productoParaVender.getId());

            if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
            
            // Le restamos existencia
            
            p.restarUnidades(productoParaVender.getCantidad());
            
            // Lo guardamos con la existencia ya restada
            
            productoDao.save(p);
            
            // Creamos un nuevo producto que será el que se guarda junto con la venta
            
    
            Detalle detalleProducto = new Detalle(productoParaVender.getCantidad(), productoParaVender.getValorVenta(), p, v);
            // Y lo guardamos
            det = detalleProducto;
            v.setFecha(new Date());

            /*detalleProducto.setVenta(v);
            v.detallesDeVenta(detalleProducto);*/
            v.setCliente(cliente);
            /*ventasDao.save(v);
            clienteDao.save(cliente);*/
            System.out.println(detalleProducto);
            System.out.println(v);
            
            detalleRepo.save(detalleProducto);
            
            //productoNuevo.save(productovendido);
        }
            //detalleRepo.save(det);
            //System.out.println(v.toString());
            //System.out.println(cliente);
            //System.out.println(v.getDetalle());
        // Al final limpiamos el carrito

        this.limpiarCanasta(request);
       
        // e indicamos una venta exitosa
        

        redirectAttrs
                .addFlashAttribute("mensaje", "Venta realizada correctamente")
                .addFlashAttribute("clase", "success");


        return"redirect:/ventas/form/"+cliente.getId();
    }

    @GetMapping("/ventasGeneral")
    public String todasLasVentas(Model model)
    {

        model.addAttribute("ventas", ventasDao.findAll());
        
        System.out.println(ventasDao.findAll());
        return "/ventas/ventasGeneral";
    }
}
