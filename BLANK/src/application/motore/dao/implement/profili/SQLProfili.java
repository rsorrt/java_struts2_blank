package application.motore.dao.implement.profili;

public class SQLProfili {
 
    public static final String getFunzioni(int abi, String nomeProfilo) {
        String query = 
            "SELECT FUNZIONI_ID FROM " + TableProfili.NAME
            + " WHERE "
            + "  ABI=" + abi
            + "  AND PROFILO='" + nomeProfilo + "'";
        return query;
    } //getFunzioni
    

    public static final String addFunzioni(int abi, String nomeProfilo, String stringFunzioni) {
        String query = 
            "INSERT INTO " + TableProfili.NAME
            + " VALUES( "
            + abi
            + ",'" + nomeProfilo + "'"
            + ",'" + stringFunzioni + "')";
        return query;
    } //getFunzioni
    
    
    public static final String updateFunzioni(int abi, String nomeProfilo, String stringFunzioni) {
        String query = 
            "UPDATE " + TableProfili.NAME
            + " SET "
            + "FUNZIONI_ID='" + stringFunzioni + "'"
            + " WHERE "
            + " ABI=" + abi
            + " AND PROFILO='" + nomeProfilo + "'";
        return query;
    } //getFunzioni



    public static final String delFunzioni(String nomeProfilo) {
        String query = 
            " DELETE FROM " + TableProfili.NAME
            + " WHERE "
            + " PROFILO='" + nomeProfilo + "'";
        return query;
    } //delFunzioni
    
    
    public static final String delFunzioni(int abi, String nomeProfilo) {
        String query = 
            " DELETE FROM " + TableProfili.NAME
            + " WHERE "
            + " ABI=" + abi
            + " AND PROFILO='" + nomeProfilo + "'";
        ;
        return query;
    } //
    
    
    public static final String profiloFunzioniExists(int abi, String nomeProfilo) {
        String query = 
            "SELECT COUNT(ABI) as COUNTER FROM " + TableProfili.NAME
            + " WHERE ABI=" + abi
            + " AND PROFILO='" + nomeProfilo + "'";
        return query;
    } //
    
    
} //END OF CLASS
