package org.example;

import java.util.Arrays;

public class StringListImpl implements StringList {

        private final String[] storage;
        private int size;

        public StringListImpl() {
            storage = new String[10];
        }


        public StringListImpl(int initSize) {
            storage = new String[initSize];
        }

        @Override
        public String add(String item) throws StorageIsFullExeption, NullItemExeption {
            validateSize();
            validateItem(item);
            storage[size++] = item;
            return item;
        }

        @Override
        public String add(int index, String item) throws StorageIsFullExeption, NullItemExeption, InvalidIndexExeption {
            validateSize();
            validateIndex(index);
            validateItem(item);
            if (index == size) {
                storage[size++] = item;
            }

            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = item;
            return item;
        }

        @Override
        public String set(int index, String item) throws NullItemExeption, InvalidIndexExeption {
            validateIndex(index);
            validateItem(item);
            storage[index] = item;
            return item;
        }

        @Override
        public String remove(String item) throws NullItemExeption, InvalidIndexExeption {
            validateItem(item);

            int index = indexOf(item);

            return remove(index);
        }

        @Override
        public String remove(int index) throws InvalidIndexExeption {
            validateIndex(index);

            String item = storage[index];

            if (index != size) {
                System.arraycopy(storage, index + 1, storage, index, size - index);
            }
            size--;
            return item;
        }

        @Override
        public boolean contains(String item) {
            return indexOf(item) != - 1;
        }

        @Override
        public int indexOf(String item) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(item)) {
                    return i;
                }
            }

            return -1;

        }

        @Override
        public int lastIndexOf(String item) {
            for (int i = size - 1; i < 0; i--) {
                if (storage[i].equals(item)) {
                    return i;
                }
            }

            return -1;
        }

        @Override
        public String get(int index) throws InvalidIndexExeption {
            validateIndex(index);
            return storage[index];
        }

        @Override
        public boolean equals(StringList otherList) {
            return Arrays.equals(this.toArray(), otherList.toArray());
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public void clear() {
            size = 0;
        }

        @Override
        public String[] toArray() {
            return Arrays.copyOf(storage, size);
        }

        private void validateItem(String item) throws NullItemExeption {
            if (item == null) {
                throw new NullItemExeption();
            }
        }

        private void validateSize() throws StorageIsFullExeption {
            if (size == storage.length) {
                throw new StorageIsFullExeption();
            }
        }


        private void validate() throws StorageIsFullExeption {
            if (size == storage.length) {
                throw new StorageIsFullExeption();
            }
        }

        private void validateIndex(int index) throws InvalidIndexExeption {
            if (index < 0 || index > size) {
                throw new InvalidIndexExeption();
            }

        }
    }

