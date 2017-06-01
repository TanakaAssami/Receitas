package assami.receitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Assami on 01/06/2017.
 */

public class Controller {
    private SQLiteDatabase db;
    private Banco banco;

    public Controller(Context context){
        banco = new Banco(context);
    }

    public String insereDado(String ingredientes, String mododepreparo, String quantidade, String nomedareceita){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(Banco.INGREDIENTES, ingredientes);
        valores.put(Banco.MODODEPREPARO, mododepreparo);
        valores.put(Banco.QUANTIDADEDEPESSOASSERV, quantidade);
        valores.put(Banco.NOMEDARECEITA, nomedareceita);


        resultado = db.insert(Banco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOMEDARECEITA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOMEDARECEITA,banco.QUANTIDADEDEPESSOASSERV,banco.INGREDIENTES};
        String where = Banco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(Banco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String ingredientes, String mododepreparo, String quantidade, String nomedareceita ){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = Banco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(Banco.INGREDIENTES, ingredientes);
        valores.put(Banco.MODODEPREPARO, mododepreparo);
        valores.put(Banco.QUANTIDADEDEPESSOASSERV, quantidade);
        valores.put(Banco.NOMEDARECEITA, nomedareceita);

        db.update(Banco.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = Banco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(Banco.TABELA,where,null);
        db.close();
    }
}
