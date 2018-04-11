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
	    genreList.insertItem(item);
	}

	public void delete(MovieDBItem item) {
		for (Genre genre : genreList) {
			if (genre.getItem().equals(item.getGenre())) {
				genre.movieList.remove(item.getTitle());
			}
		}
	}

	public MyLinkedList<MovieDBItem> search(String term) {
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		for (Genre genre : genreList) {
			for (String title : genre.movieList) {
				if (title.contains(term))
					results.add(new MovieDBItem(genre.getItem(), title));
			}
		}
		// Printing search results is the responsibility of SearchCmd class.
		// So you must not use System.out in this method to achieve specs of the assignment.
		return results;
	}

	public MyLinkedList<MovieDBItem> items() {
		MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

		for (Genre genre : genreList) {
			for (String title : genre.movieList)
				results.add(new MovieDBItem(genre.getItem(), title));
		}
		// Printing movie items is the responsibility of PrintCmd class.
		// So you must not use System.out in this method to achieve specs of the assignment.

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
	    return getItem() == null ? 0 : getItem().hashCode();
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
}

class GenreList extends MyLinkedList<Genre> implements ListInterface<Genre>{
	//Node<Genre> head;
	//int numItems;

	public void insertItem(MovieDBItem item) {
		Genre newGenre = new Genre(item.getGenre());
		Node<Genre> prev = head;
		Node<Genre> curr = head.getNext();
    	while (curr != null && newGenre.compareTo(curr.getItem()) > 0) {
    		prev = curr;
    		curr = curr.getNext();
		}
		//insert new genre if not exists
		if (curr == null || newGenre.compareTo(curr.getItem()) < 0) {
    	    addItemAfter(newGenre, prev);
    		curr = prev.getNext();
		}
		curr.getItem().movieList.add(item.getTitle());
	}
}
class MovieList  extends MyLinkedList<String> implements ListInterface<String> {
	// Node<String> head;
	// int numItems;

    //add movie mantaining sorted order
    @Override
	public void add(String title) {
	    //movie with title will be added after cur
		Node<String> prev = head;
		Node<String> curr = head.getNext();
    	while (curr != null && title.compareTo(curr.getItem()) > 0) {
    		prev = curr;
    		curr = curr.getNext();
		}
		if (curr == null || title.compareTo(curr.getItem()) < 0) {
    	    addItemAfter(title, prev);
		}
	}
}