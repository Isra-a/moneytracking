package com.unibo.koci.moneytracking.Entities;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by koale on 31/08/17.
 */

@Entity
public class PlannedItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Date date;

    @NotNull
    private double amount;

    @NotNull
    @Index
    private Long categoryID;

    @NotNull
    @Index
    private Long locationID;

    @ToOne(joinProperty = "categoryID")
    private Category category;

    @ToOne(joinProperty = "locationID")
    private Location location;

    @NotNull
    private String occurrence; // daily - weekly - monthly - yearly

    @NotNull
    private Integer repeat; // how much time repeat this planned item

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1660658002)
    private transient PlannedItemDao myDao;

    @Generated(hash = 6184275)
    public PlannedItem(Long id, @NotNull String name, @NotNull String description, @NotNull Date date, double amount, @NotNull Long categoryID, @NotNull Long locationID, @NotNull String occurrence, @NotNull Integer repeat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.categoryID = categoryID;
        this.locationID = locationID;
        this.occurrence = occurrence;
        this.repeat = repeat;
    }

    @Generated(hash = 1232195301)
    public PlannedItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getLocationID() {
        return this.locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public String getOccurrence() {
        return this.occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public Integer getRepeat() {
        return this.repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    @Generated(hash = 1372501278)
    private transient Long category__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1790867297)
    public Category getCategory() {
        Long __key = this.categoryID;
        if (category__resolvedKey == null || !category__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoryDao targetDao = daoSession.getCategoryDao();
            Category categoryNew = targetDao.load(__key);
            synchronized (this) {
                category = categoryNew;
                category__resolvedKey = __key;
            }
        }
        return category;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 264614216)
    public void setCategory(@NotNull Category category) {
        if (category == null) {
            throw new DaoException("To-one property 'categoryID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.category = category;
            categoryID = category.getCategoryID();
            category__resolvedKey = categoryID;
        }
    }

    @Generated(hash = 1068795426)
    private transient Long location__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1847191610)
    public Location getLocation() {
        Long __key = this.locationID;
        if (location__resolvedKey == null || !location__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LocationDao targetDao = daoSession.getLocationDao();
            Location locationNew = targetDao.load(__key);
            synchronized (this) {
                location = locationNew;
                location__resolvedKey = __key;
            }
        }
        return location;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 43823444)
    public void setLocation(@NotNull Location location) {
        if (location == null) {
            throw new DaoException("To-one property 'locationID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.location = location;
            locationID = location.getLocationID();
            location__resolvedKey = locationID;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 243494952)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlannedItemDao() : null;
    }

}