import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * <p>
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를
 * 유지하는 데이터베이스이다.
 */
public class MovieDB {

	private GenreList genreList;

	public MovieDB() {
		genreList = new GenreList();
	}

	public void insert(MovieDBItem item) {
		// Insert the given item to the MovieDB.
	    genreList.insertItem(item);
		// Printing functionality is provided for the sake of debugging.
		// This code should be removed before submitting your work.
		System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
	}

	public void delete(MovieDBItem item) {
		// FIXME implement this
		// Remove the given item from the MovieDB.
		for (Genre genre : genreList) {
			if (genre.getItem().equals(item.getGenre())) {
				genre.deleteMovie(item.getTitle());
			}
		}
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
    private MovieList movieList;

	public Genre(String name) {
		super(name);
		setItem(name);
		movieList = new MovieList();
	}
	
	@Override
	public int compareTo(Genre o) {
	    if (getItem() == null) {
	    	if (o.getItem() == null)
	    		return 0;
	    	else
	    		return -1;
		} else if (o.getItem() == null)
			return 1;

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

	public void deleteMovie(String title) {
		movieList.remove(title);
	}
}

class GenreList extends MyLinkedList<Genre> implements ListInterface<Genre>{
	//Node<Genre> head;
	//int numItems;

	public void insertItem(MovieDBItem item) {
		Genre newGenre = new Genre(item.getGenre());
		Node<Genre> prev = head;
		Node<Genre> curr = head.getNext();
    	while (curr != null && newGenre.compareTo(curr.getItem()) < 0) {
    		prev = curr;
    		curr = curr.getNext();
		}
		//insert new genre if not exists
		if (curr == null || newGenre.compareTo(curr.getItem()) < 0) {
    	    addItemAfter(newGenre, prev);
    		curr = prev.getNext();
		}
		curr.getItem().addMovie(item.getTitle());
	}
}
class MovieList  extends MyLinkedList<String> implements ListInterface<String> {
	// Node<String> head;
	// int numItems;

    //add movie mantaining sorted order
	public void addMovie(String title) {
	    //movie with title will be added after cur
		Node<String> prev = head;
		Node<String> curr = head;
    	while (prev != null && title.compareTo(curr.getItem()) < 0) {
    		prev = curr;
    		curr = curr.getNext();
		}
		if (curr == null || title.compareTo(curr.getItem()) < 0) {
    	    addItemAfter(title, prev);
		}
	}
}