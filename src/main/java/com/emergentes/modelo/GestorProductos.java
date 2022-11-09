package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorProductos {
    private ArrayList<Productos> lista;
    public GestorProductos(){
        lista = new ArrayList<Productos>();
    }

    public ArrayList<Productos> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Productos> lista) {
        this.lista = lista;
    }
    public void insertarTarea(Productos item){
        lista.add(item);
    }
    public void modificarTarea(int pos,Productos item){
        lista.set(pos, item);
    }
    public void eliminarTarea(int pos){
        lista.remove(pos);
    }
    public int obtieneId(){
        int idaux=0;
        for (Productos item: lista) {
            idaux=item.getId();
        }
        return idaux+1;
    }
    public int ubicarTareas(int id){
        int pos=-1;
        Iterator<Productos> it=lista.iterator();
        while(it.hasNext()){
            ++pos;
            Productos aux = it.next();
            if(aux.getId()==id){
                break;
            }
        }
        return pos;
        
    }
}