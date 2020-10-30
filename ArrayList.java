import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<aList> implements List<aList> {
    public static final int limit = 4;
    private aList[] simon;
    private int size = 0;

    public ArrayList() {
        this(limit);
    }

    public ArrayList(int capacity) {
        simon = (aList[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    @Override
    public aList get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return simon[i];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public aList set(int i, aList aList) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        aList ph = simon[i];
        simon[i] = aList;
        return ph;
    }

    /* public static void makeNumVanish() {    Adds new lines to console to hide Simon words,
        for (int i = 0; i <= 300; i++) {       Not needed anymore, new method to erase Simon words works.
            System.out.println();
        }
    } */

    @Override
    public void add(int i, aList aList) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == simon.length) {
            resize(2 * simon.length);
        }
        int s = size - 1;
        while (s >= i) {
            simon[s + 1] = simon[s];
            simon[i] = aList;
            size++;
            s--;
        }
    }

    public void add(aList aList) {
        if (size == simon.length) {
            resize(2 * simon.length);
        }
        simon[size] = aList;
        size++;
    }

    @Override
    public aList remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        aList ph = simon[i];
        if (size - 1 - i >= 0) {
            System.arraycopy(simon, i + 1, simon, i, size - 1 - i);
        }
        simon[size - 1] = null;
        size--;
        return ph;
    }

    protected void resize(int capacity) {
        aList[] ph = (aList[]) new Object[capacity];
        if (size >= 0) {
            System.arraycopy(simon, 0, ph, 0, size);
        }
        simon = ph;
    }

    public void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException("ILLEGAL INDEX: " + i);
    }

    @Override
    public Iterator<aList> iterator() {
        return new ArrayIterator();
    }

    public boolean equals(ArrayList<aList> s) {
        boolean back = false;
        int i = 0;
        while (i < size()) {
            String s1 = (String) this.get(i);
            String s2 = (String) s.get(i);
            if (s1.equalsIgnoreCase(s2)) back = true;
            else back = false;
            i++;
        }
        return back;
    }

    private class ArrayIterator implements Iterator<aList> {
        private int j = 0;
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            if (j < size) return true;
            else return false;
        }

        public aList next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("NO ELEMENT AHEAD");
            removable = true;
            return simon[j++];

        }

        @Override
        public void remove() throws IllegalStateException {
            assert removable : "nothing to remove";
            ArrayList.this.remove(j - 1);
            j--;
            removable = false;
        }
    }
}
