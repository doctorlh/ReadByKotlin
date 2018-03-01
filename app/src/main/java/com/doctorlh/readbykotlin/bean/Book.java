package com.doctorlh.readbykotlin.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
@Entity
public class Book implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @SerializedName("_id")
    public String bookId;

    public String title;

    public String cover;

    public String author;

    public int lastRead;

    @Generated(hash = 941659208)
    public Book(String bookId, String title, String cover, String author,
            int lastRead) {
        this.bookId = bookId;
        this.title = title;
        this.cover = cover;
        this.author = author;
        this.lastRead = lastRead;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Book) {
            Book book = (Book) obj;
            return book.bookId == this.bookId;
        }
        return super.equals(obj);
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLastRead() {
        return this.lastRead;
    }

    public void setLastRead(int lastRead) {
        this.lastRead = lastRead;
    }
}
