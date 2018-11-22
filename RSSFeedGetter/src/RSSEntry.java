import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;


@Entity @Table( name="RSSEntrys")
@RequestScoped @ManagedBean( name="RSSEntry")
public class RSSEntry {
    @Id
    private String isbn;
    private String title;
    private int year;

    /**
     * Default constructor, required for entity classes
     */
    public RSSEntry() {
    }

    /**
     * Constructor
     */
    public RSSEntry(String isbn, String title, int year) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setYear(year);
    }

    /**
     * Getters and setters
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Create a human readable serialization.
     */
    public String toString() {
        return "{ isbn: '" + this.isbn + "', title:'" + this.title + "', year: '"
                + this.year + "'}";
    }

    /**
     * Retrieve all RSSEntry records from the RSSEntrys table.
     *
     * @param em reference to the entity manager
     * @return the list of all RSSEntry records
     */
    public static List<RSSEntry> retrieveAll(EntityManager em) {
        TypedQuery<RSSEntry> query = em.createQuery("SELECT b FROM RSSEntry b", RSSEntry.class);
        List<RSSEntry> RSSEntrys = query.getResultList();
        System.out.println("RSSEntry.retrieveAll: " + RSSEntrys.size()
                + " RSSEntrys were loaded from DB.");
        return RSSEntrys;
    }

    /**
     * Retrieve a RSSEntry record from the RSSEntrys table.
     *
     * @param em   reference to the entity manager
     * @param isbn the RSSEntry's ISBN
     * @return the RSSEntry with the given ISBN
     */
    public static RSSEntry retrieve(EntityManager em, String isbn) {
        RSSEntry RSSEntry = em.find(RSSEntry.class, isbn);
        if (RSSEntry != null) {
            System.out.println("RSSEntry.retrieve: loaded RSSEntry " + RSSEntry);
        }
        return RSSEntry;
    }

    /**
     * Create a RSSEntry instance.
     *
     * @param em    reference to the entity manager
     * @param ut    reference to the user transaction
     * @param isbn  the ISBN of the RSSEntry to create
     * @param title the title of the RSSEntry to create
     * @param year  the year of the RSSEntry to create
     * @throws Exception
     */
    public static void add(EntityManager em, UserTransaction ut, String isbn,
                           String title, int year) throws Exception {
        ut.begin();
        RSSEntry RSSEntry = new RSSEntry(isbn, title, year);
        em.persist(RSSEntry);
        ut.commit();
        System.out.println("RSSEntry.add: the RSSEntry " + RSSEntry + " was saved.");
    }

    /**
     * Update a RSSEntry instance
     *
     * @param em    reference to the entity manager
     * @param ut    reference to the user transaction
     * @param isbn  the ISBN of the RSSEntry to update
     * @param title the title of the RSSEntry to update
     * @param year  the year of the RSSEntry to update
     * @throws Exception
     */
    public static void update(EntityManager em, UserTransaction ut, String isbn,
                              String title, int year) throws Exception {
        ut.begin();
        RSSEntry RSSEntry = em.find(RSSEntry.class, isbn);
        if (title != null && !title.equals(RSSEntry.title)) {
            RSSEntry.setTitle(title);
        }
        if (year != RSSEntry.year) {
            RSSEntry.setYear(year);
        }
        ut.commit();
        System.out.println("RSSEntry.update: the RSSEntry " + RSSEntry + " was updated.");
    }
}

