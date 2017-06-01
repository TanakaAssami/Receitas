package assami.receitas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Assami on 01/06/2017.
 */

public class Banco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "receitas.db";
    public static final String TABELA = "receitas";
    public static final String ID = "_id";
    public static final String NOMEDARECEITA = "nomedareceita";
    public static final String INGREDIENTES = "ingredientes";
    public static final String MODODEPREPARO = "mododepreparo";
    public static final String QUANTIDADEDEPESSOASSERV = "quantidadedePessoasServidas";
    public static final int VERSAO = 1;

    public Banco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOMEDARECEITA + " text,"
                + INGREDIENTES + " text,"
                + MODODEPREPARO + " text"
                + QUANTIDADEDEPESSOASSERV + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
