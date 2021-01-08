package com.codegym.blog.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "blogs")
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 55)
    @NotEmpty(message = "Title must not be null or empty!")
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    @NotEmpty(message = "Message is required.")
    private String content;
    @Basic(optional = false)
    @NotEmpty(message = "Summary is required.")
    private String imageURL;
    @Column(columnDefinition = "boolean default false")
    private Boolean active;
    private Date lastModifiedDate;
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    @Transient
    private MultipartFile imageData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String image) {
        this.imageURL = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public MultipartFile getImageData() {
        return imageData;
    }

    public void setImageData(MultipartFile imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", active=" + active +
                ", lastModifiedDate=" + lastModifiedDate +
                ", category=" + category +
                ", imageData=" + imageData +
                '}';
    }
}
