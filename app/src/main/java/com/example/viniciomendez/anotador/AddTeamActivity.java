package com.example.viniciomendez.anotador;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AddTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        List<Pais> users = new List<Pais>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Pais> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(Pais pais) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Pais> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Pais> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Pais get(int i) {
                return null;
            }

            @Override
            public Pais set(int i, Pais pais) {
                return null;
            }

            @Override
            public void add(int i, Pais pais) {

            }

            @Override
            public Pais remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Pais> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Pais> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Pais> subList(int i, int i1) {
                return null;
            }
        };
        users.add(new Pais("Republica Dominicana","DOM"));
        users.add(new Pais("Estados Unidos","USA"));
        users.add(new Pais("Venezuela","VEN"));
        users.add(new Pais("Colombia","COL"));
        ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, users);

        Spinner userSpinner = (Spinner) findViewById(R.id.spinner2);
        userSpinner.setAdapter(userAdapter);



// And to get the actual User object that was selected, you can do this.
        Pais user = (Pais) ( (Spinner) findViewById(R.id.spinner2) ).getSelectedItem();
    }
}
