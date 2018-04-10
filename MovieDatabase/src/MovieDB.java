import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * <p>
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를
 * 유지하는 데이터베이스이다.
 */
public class MovieDB {

	MyLinkedList<Genre> genres;

	public MovieDB() {
		genres = new MyLinkedList<>();
	}

	public void insert(MovieDBItem item) {
		Genre newGenre = new Genre(item.getGenre());
		Node<Genre> cur = genres.head;
    	while (cur.getNext() != null && newGenre.compareTo(cur.getNext().getItem()) < 0) {
    		cur = cur.getNext();
		}
		if (cur.getNext() == null || newGenre.compareTo(cur.getNext().getItem()) < 0) {
    		genres.insertAfter(cur, newGenre);
    		cur = cur.getNext();
		}
		cur.getItem().addMovie(item.getTitle());
		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
	}

	public void delete(MovieDBItem item) {
		// FIXME implement this
		// Remove the given item from the MovieDB.

		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", item.getGenre(), item.getTitle());
	}

	public MyLinkedList<MovieDBItem> search(String term) {
		// FIXME implement this
		// Search the given term from the MovieDB.
		// You should return a linked list of MovieDBItem.
		// The search command is handled at SearchCmd class.

		// Printing search results is the responsibility of SearchCmd class.
		// So you must not use System.out in this method to achieve specs of the assignment.

		// This tracing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);

		// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
		// This code is supplied for avoiding compilation error.
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

		return results;
	}

	public MyLinkedList<MovieDBItem> items() {
		// FIXME implement this
		// Search the given term from the MovieDatabase.
		// You should return a linked list of QueryResult.
		// The print command is handled at PrintCmd class.

		// Printing movie items is the responsibility of PrintCmd class.
		// So you must not use System.out in this method to achieve specs of the assignment.

		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		System.err.printf("[trace] MovieDB: ITEMS\n");

		// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
		// This code is supplied for avoiding compilation error.
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

		return results;
	}
}

class Genre extends Node<String> implements Comparable<Genre> {
    //private String item;
	//private Node<String> next;
    MovieList movieList;

	public Genre(String name) {
		super(name);
		setItem(name);
		movieList = new MovieList(name);
	}
	
	@Override
	public int compareTo(Genre o) {
	    return getItem().compareTo(o.getItem());
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	    	return true;
	    if (obj == null)
	    	return false;
	    if (getClass() != obj.getClass())
	    	return false;
	    Genre other = (Genre) obj;
	    if (getItem() == null) {
	    	if (other.getItem() != null)
	    		return false;
		} else if (!getItem().equals(other.getItem()))
			return false;
	    return true;
	}

	public void addMovie(String title) {
		movieList.addMovie(title);
	}
}

class MovieList  extends MyLinkedList<String> implements ListInterface<String> {

	Node<String> head;
	int numMovies;

	public MovieList(String genreName) {
	    this.genre = new Genre(genreName);
	}

	@Override
	public Iterator<String> iterator() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public int size() {
	    return numMovies;
	}

	@Override
	public void add(String item) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	public void addSorted(String item) {
		//ToDo
	}

	@Override
	public String first() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public void removeAll() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	public void addMovie(String title) {
	    //movie with title will be added after cur
		Node<String> cur = head;
    	while (cur.getNext() != null && title.compareTo(cur.getNext().getItem()) < 0) {
    		cur = cur.getNext();
		}
		if (cur.getNext() == null || title.compareTo(cur.getNext().getItem()) < 0) {
    	    Node<String> newNode = new Node<>(title);
    	    newNode.setNext(cur.getNext());
    	    cur.setNext(newNode);
    	    numMovies++;
		}
	}
}