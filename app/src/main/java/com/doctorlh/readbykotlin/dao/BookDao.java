package com.doctorlh.readbykotlin.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.doctorlh.readbykotlin.bean.Book;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK".
*/
public class BookDao extends AbstractDao<Book, String> {

    public static final String TABLENAME = "BOOK";

    /**
     * Properties of entity Book.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BookId = new Property(0, String.class, "bookId", true, "BOOK_ID");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Cover = new Property(2, String.class, "cover", false, "COVER");
        public final static Property Author = new Property(3, String.class, "author", false, "AUTHOR");
        public final static Property LastRead = new Property(4, int.class, "lastRead", false, "LAST_READ");
    }


    public BookDao(DaoConfig config) {
        super(config);
    }
    
    public BookDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK\" (" + //
                "\"BOOK_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: bookId
                "\"TITLE\" TEXT," + // 1: title
                "\"COVER\" TEXT," + // 2: cover
                "\"AUTHOR\" TEXT," + // 3: author
                "\"LAST_READ\" INTEGER NOT NULL );"); // 4: lastRead
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Book entity) {
        stmt.clearBindings();
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(1, bookId);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(3, cover);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
        stmt.bindLong(5, entity.getLastRead());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Book entity) {
        stmt.clearBindings();
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(1, bookId);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(3, cover);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(4, author);
        }
        stmt.bindLong(5, entity.getLastRead());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Book readEntity(Cursor cursor, int offset) {
        Book entity = new Book( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // bookId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cover
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // author
            cursor.getInt(offset + 4) // lastRead
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Book entity, int offset) {
        entity.setBookId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCover(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAuthor(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLastRead(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Book entity, long rowId) {
        return entity.getBookId();
    }
    
    @Override
    public String getKey(Book entity) {
        if(entity != null) {
            return entity.getBookId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Book entity) {
        return entity.getBookId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
