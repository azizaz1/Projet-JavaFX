/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package azyz.IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azizf
 */
public interface IService<T> {
    
    public void ajouter(T t);
    public List<T> afficher() throws SQLException;

    public void supprimer(int id) throws SQLException;

    public void modifier(int id, T t) throws SQLException;
    
}
