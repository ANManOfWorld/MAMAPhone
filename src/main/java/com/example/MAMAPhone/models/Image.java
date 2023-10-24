package com.example.MAMAPhone.models;

//import jakarta.persistence.*;

import javax.persistence.*;
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; // foreign key здесь
    @Column(name = "size")
    private Long size;
    @Column(name = "name")
    private String name;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;
    @Lob //указание на большие объекты
    //@Column(name="bytes", columnDefinition = "longblob") //для разрешения проблемы малости массива байтов - вылетает 500
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Rate rate;

    public Image(Long id, Long size, String name, String fileName, String contentType, boolean isPreviewImage, byte[] bytes, Rate rate) {
        this.id = id;
        this.size = size;
        this.name = name;
        this.fileName = fileName;
        this.contentType = contentType;
        this.isPreviewImage = isPreviewImage;
        this.bytes = bytes;
        this.rate = rate;
    }

    public Image() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getContentType() {
        return this.contentType;
    }

    public boolean isPreviewImage() {
        return this.isPreviewImage;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public Rate getRate() {
        return this.rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setPreviewImage(boolean isPreviewImage) {
        this.isPreviewImage = isPreviewImage;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Image)) return false;
        final Image other = (Image) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) return false;
        final Object this$contentType = this.getContentType();
        final Object other$contentType = other.getContentType();
        if (this$contentType == null ? other$contentType != null : !this$contentType.equals(other$contentType))
            return false;
        if (this.isPreviewImage() != other.isPreviewImage()) return false;
        if (!java.util.Arrays.equals(this.getBytes(), other.getBytes())) return false;
        final Object this$rate = this.getRate();
        final Object other$rate = other.getRate();
        if (this$rate == null ? other$rate != null : !this$rate.equals(other$rate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Image;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $fileName = this.getFileName();
        result = result * PRIME + ($fileName == null ? 43 : $fileName.hashCode());
        final Object $contentType = this.getContentType();
        result = result * PRIME + ($contentType == null ? 43 : $contentType.hashCode());
        result = result * PRIME + (this.isPreviewImage() ? 79 : 97);
        result = result * PRIME + java.util.Arrays.hashCode(this.getBytes());
        final Object $rate = this.getRate();
        result = result * PRIME + ($rate == null ? 43 : $rate.hashCode());
        return result;
    }

    public String toString() {
        return "Image(id=" + this.getId() + ", size=" + this.getSize() + ", name=" + this.getName() + ", fileName=" + this.getFileName() + ", contentType=" + this.getContentType() + ", isPreviewImage=" + this.isPreviewImage() + ", bytes=" + java.util.Arrays.toString(this.getBytes()) + ", rate=" + this.getRate() + ")";
    }
}
