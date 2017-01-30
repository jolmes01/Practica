package controller.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.dao.ModelUsuarios;
import model.dto.Usuario;

@ManagedBean(name = "usuariosBean")
@SessionScoped
public class UsuariosBean extends BaseBean{
    
    private Usuario user;
    private boolean sessionAllow;
    private Usuario newUser;
    private List<Usuario> usuariosList;
    private ModelUsuarios mu;
    
    @PostConstruct
    public void init() {
        usuariosList = new ArrayList<>();
        user = new Usuario();
        newUser = new Usuario();
        sessionAllow = false;
        mu = new ModelUsuarios();
    }
    
    //********************** Direccionamiento a documentos xhtml
    public String principal() {
        setAccion(ACC_CREAR);
        newUser = new Usuario();
        return "/Usuarios/usuario.xhtml";
    }
    
    public String editar(Usuario dto){
        newUser = dto;
        setAccion(ACC_ACTUALIZAR);
        return "/Usuarios/usuario.xhtml";
    }
    
    public String consultar() {
        usuariosList = mu.ReadAll();
        return "/Usuarios/usuarios.xhtml";
    }
    
    public String principalUsuarios(){
        return "/Usuarios/usuarios.xhtml";
    }
    
    //********************** Procesamiento de la información
    public String datosUsuario(){
        String redirectTo = "/Usuarios/usuario.xhtml";
        int resultado = 0;
        
        if(isModoCrear()){ //Si la acción es registro de un nuevo usuario, se creará un nuevo usuario
            resultado = mu.Create(newUser);
            usuariosList.add(newUser);
        }
        if(isModoActualizar()) resultado = mu.Update(newUser); //Si la acción es actualizar un usuario, se actualizarán los datos del usuario
        
        if(resultado != 0){
            success(getAccionN() + " usuario.", newUser.getNombre() + " " + newUser.getApellidoPaterno()+ " " + newUser.getApellidoMaterno() + " se ha guardado correctamente");
            setAccion(ACC_CREAR);
            redirectTo = "/Usuarios/usuarios.xhtml";
            newUser = new Usuario();
        }else{
            if(isModoCrear())
                warning("Usuario existente.", "El usuario no se puede registrar debido a que ya existe");
            else
                warning(getAccion() + " usuario.", "Falló la conexión y no se pudieron guardar los datos correctamente");
        }
        return redirectTo;
    }
    
    public void eliminar(Usuario dto){
        if(mu.Delete(dto) != 0){
            usuariosList.remove(dto);
            success("Eliminar usuario.", dto.getNombre() + " se ha dado de baja del sistema correctamente");
        }else{
            warning("Eliminar usuario.", "El usuario no se pudo eliminar");
        }
        
    }

    
    //********************** Getters & Setters
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public boolean isSessionAllow() {
        return sessionAllow;
    }

    public void setSessionAllow(boolean sessionAllow) {
        this.sessionAllow = sessionAllow;
    }

    public Usuario getNewUser() {
        return newUser;
    }

    public void setNewUser(Usuario newUser) {
        this.newUser = newUser;
    }

    public List<Usuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuario> usuariosList) {
        this.usuariosList = usuariosList;
    }
}