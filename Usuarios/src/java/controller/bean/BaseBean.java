package controller.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseBean implements Serializable{

    protected static final String ACC_CREAR = "CREAR";
    protected static final String ACC_ACTUALIZAR = "ACTUALIZAR";
    protected static final String ACC_MODIFICAR = "MODIFICAR";
    protected static String accion;

    public BaseBean() {
        
    }

    protected void error(String idMensaje, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(
                idMensaje,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, idMensaje,
                        mensaje));
    }
    
    protected void warning(String idMensaje, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(
                idMensaje,
                new FacesMessage(FacesMessage.SEVERITY_WARN, idMensaje,
                        mensaje));
    }
    
    protected void success(String idMensaje, String mensaje){
        FacesContext.getCurrentInstance().addMessage(
                idMensaje,
                new FacesMessage(FacesMessage.SEVERITY_INFO, idMensaje,
                        mensaje));
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }
    
    public String getAccionN(){
        if(isModoCrear()) return "Crear";
        if(isModoActualizar()) return "Actualizar";
        else return "Modificar";
    }

    public boolean isModoCrear() {
        if (accion != null) {
            return accion.equals(ACC_CREAR);
        } else {
            return false;
        }
    }

    public boolean isModoActualizar() {
        if (accion != null) {
            return accion.equals(ACC_ACTUALIZAR);
        } else {
            return false;
        }
    }
    
    public boolean isModoModificar() {
        if (accion != null) {
            return accion.equals(ACC_MODIFICAR);
        } else {
            return false;
        }
    }
}