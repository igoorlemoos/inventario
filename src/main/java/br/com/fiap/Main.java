package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

//        inserindoValores(manager);

//        procurarPorIdInventario(manager);

        listarTodosBens(manager);

        manager.close();
        factory.close();

    }

    private static void listarTodosBens(EntityManager manager) {
        List<Bem> listaBens = manager.createQuery("SELECT b FROM Bem b").getResultList();
        listaBens.forEach(System.out::println);
    }

    private static void procurarPorIdInventario(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Informe o id que deseja buscar:"));
        Inventario buscarIdInventario = manager.find(Inventario.class, id);

        System.out.println(buscarIdInventario);
    }

    private static void inserindoValores(EntityManager manager) {
        TipoDeBem tipoDeBem = new TipoDeBem(null, "Casa");
        Departamento departamento = new Departamento(null, "Imobiliario");

        Inventario inventario = new Inventario(null, departamento, LocalDate.now(),
                null, "VALOR DE VENDA");
        Inventario inventario2 = new Inventario(null, departamento, LocalDate.now(),
                null, "VALOR DE COMISSÃO");

        Bem bem = new Bem(null, "Nome", tipoDeBem, "Etiqueta", departamento, LocalDate.now());
        Bem bem2 = new Bem(null, "Nome2", tipoDeBem, "Etiqueta2", departamento, LocalDate.now());

        manager.getTransaction().begin();

        manager.persist(bem);
        manager.persist(bem2);
        manager.persist(inventario);
        manager.persist(inventario2);


        manager.getTransaction().commit();
    }
}