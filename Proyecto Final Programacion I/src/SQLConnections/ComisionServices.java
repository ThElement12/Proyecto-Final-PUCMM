package SQLConnections;

import Logico.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class ComisionServices {

    public static ArrayList<Comision> getComisiones() throws SQLException {
        ArrayList<Comision> comisiones = new ArrayList<>();
        Connection myConnection = Conexion.getConnection();
        CallableStatement cstmt = null;
        ResultSet rs = null;

        cstmt = myConnection.prepareCall("{call Rpt_ListarComisiones_Y_Juez}");
        cstmt.execute();
        Evento evento = null;
        Comision comision = null;
        rs = cstmt.getResultSet();
        while(rs.next()){
            if(evento == null || !evento.getNombre().equalsIgnoreCase(rs.getString("Nombre"))){
                evento = PUCMM.pucmm().buscarEventoPorNombre(rs.getString("Nombre"));
            }
            else{

                if(comision == null){
                    comision = new Comision(rs.getString("NombreArea"),rs.getString("TemaComision"), new ArrayList<Persona>());
                }
                else if(!comision.getTema().equalsIgnoreCase(rs.getString("TemaComision"))){
                    comisiones.add(comision);
                    comision = new Comision(rs.getString("NombreArea"),rs.getString("TemaComision"), new ArrayList<Persona>());

                }
                else{
                    if(rs.getString("TipoPersona").equalsIgnoreCase("Juez")){
                        Juez juez = (Juez) PUCMM.pucmm().getPersonaByCedula(rs.getString("Cedula"));
                        comision.insertarJuez(juez);
                    }
                    else{
                        Participante participante = (Participante) PUCMM.pucmm().getPersonaByCedula(rs.getString("Cedula"));
                        comision.getMisMiembros().add(participante);
                    }
                }
            }

        }
        return comisiones;
    }
    /*
    * TODO:
    *  Que guarde la foto en la bd
    * */
    public static ArrayList<Persona> getPersonasByComision(int id, String area) throws SQLException{
        ArrayList<Persona> personas = new ArrayList<>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection myConnection = Conexion.getConnection();

        cstmt = myConnection.prepareCall("{call ListarMiembrosComision(?)}");
        cstmt.setInt("IdComision", id);
        boolean results = cstmt.execute();
        int rowsAffected = 0;

        while(results || rowsAffected != -1){
            if(results){
                rs = cstmt.getResultSet();
                break;
            }else{
                rowsAffected = cstmt.getUpdateCount();
            }
            results = cstmt.getMoreResults();
        }
        while(rs.next()){
            if(rs.getString("TipoPersona").equalsIgnoreCase("Juez")){
                Juez juez = new Juez(rs.getString("Cedula"),rs.getString("Nombre"),
                        rs.getString("Telefono"),area,null);
                personas.add(juez);
            }else{
                Participante participante = new Participante(rs.getString("Cedula"),rs.getString("Nombre"),
                        rs.getString("Telefono"),area,null);
                personas.add(participante);
            }
        }
        rs.close();
        cstmt.close();
        myConnection.close();
        return personas;
    }

    public static void setComision(Comision comision, Evento evento) throws SQLException{
        Connection myConnection = Conexion.getConnection();
        CallableStatement cstmt = null;

        cstmt = myConnection.prepareCall("{call RegistrarComision(?, ?, ?)}");
        cstmt.setInt("IdEvento", Integer.parseInt(evento.getId()));
        cstmt.setInt("IdArea", PUCMM.pucmm().getMisAreas().indexOf(comision.getArea()));
        cstmt.setString("Tema", comision.getTema());
        cstmt.setString("CedulaJuez",comision.getJuez().getCedula());

        cstmt.executeUpdate();
        cstmt.close();
        myConnection.close();
    }
    public static void setMiembrosComision(Persona miembro, Comision comision, Evento evento, Trabajo trabajo) throws SQLException{
        Connection myConnection = Conexion.getConnection();
        CallableStatement cstmt = null;

        cstmt = myConnection.prepareCall("{call RegistrarMiembroComision(?,?, ?, ?, ?)}");
        cstmt.setInt("IdComision",comision.getId());
        cstmt.setInt("IdEvento",Integer.parseInt(evento.getId()));
        cstmt.setInt("IdArea", PUCMM.pucmm().getMisAreas().indexOf(comision.getArea()));
        cstmt.setString("Cedula", miembro.getCedula());
        cstmt.setInt("IdTrabajo", PUCMM.pucmm().getMisTiposTrabajo().indexOf(trabajo.getPosicion()));

        cstmt.executeUpdate();
        cstmt.close();
        myConnection.close();
    }
}

