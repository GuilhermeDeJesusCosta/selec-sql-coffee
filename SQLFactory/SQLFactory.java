public class SQLFactory{
    public static void main(String[] args){

            
        Produto mercadoria = new Produto();
        mercadoria.id = 99;
        mercadoria.descricao = "Coca Cola";
        mercadoria.preco = 10.99;
        
/*         Database.executarSQL(mercadoria.insertSQL());
 */
        boolean run = Database.runSelectInSQL("produtos", "99");

        if (run) {
        } 

        else {
            System.out.println("Algo de errado não está certo");
        }
    }
}
