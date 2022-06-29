import java.sql.*;
import java.sql.DriverManager;
/* import java.sql.SQLException; */

public class Database {


    private static Connection connect(){
        //ATUALIZAR DIRETORIO CASO RODAR EM OUTRA PASTA
        String url = "jdbc:sqlite:C:\\Users\\Asus\\Desktop\\N3POO\\SQLFactory\\db\\data.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static boolean inserir(SQLClass registro){
        return executarSQL( registro.insertSQL() );
    }
    public static boolean atualizar(SQLClass registro){
        return executarSQL( registro.updateSQL() );
    }
    public static boolean deletar(SQLClass registro){
        return executarSQL( registro.deleteSQL() );
    }
    /* public static boolean abrir(SQLClass registro, int id){
        return executarSQL( registro.selectSQL() );
    }     */
    
    
    public static boolean executarSQL( String sql ){
        boolean ok = false;

        Connection currentConn = connect();
        try {
            Statement statement = currentConn.createStatement();
            statement.execute(sql);
            currentConn.close();
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;            
        }
        
        return ok;
        
    }
    public static boolean runSelectInSQL( String tableName, String id ){
        
        boolean ok = false;
        Connection currentConn = connect();
        try {
            Statement selectStmt = currentConn.createStatement();
            String retorno =  "select * from "+ tableName +" where id= "+id;
            ResultSet rs = selectStmt.executeQuery(retorno);
            ResultSetMetaData metadata = rs.getMetaData(); 
            int columnCount = metadata.getColumnCount();
            System.out.println("--- Itens da tabela " +  tableName +" com o id: " + id + " ---");

            for (int i=1; i<=columnCount; i++) 
            {
                String columnName = metadata.getColumnName(i);
                System.out.print(columnName + ": ");
                System.out.println(rs.getString(columnName));
                
            }
            System.out.println("===================Execucao_Finalizada=========================");
            currentConn.close();
            ok = true;
        } /*  */catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        return ok;
    }
    public static boolean executarSQL(String string, Object id) {
        return false;
    }

    
}
