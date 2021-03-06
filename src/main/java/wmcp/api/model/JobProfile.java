package wmcp.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Contains the methods and fields for JobProfile entities.
 *
 * @author tlmader.dev@gmail.com
 * @since 2016-11-21
 */
@Entity
@JsonIgnoreProperties({"required_skill"})
public class JobProfile extends BaseEntity {

    @Id
    @Column(name = "jp_code")
    private String id;
    @Column(name = "jp_title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "avg_pay")
    private Float avgPay;
    @JsonProperty("requiredSkills")
    @ManyToMany
    @JoinTable(
            name = "required_skill",
            joinColumns = @JoinColumn(name = "jp_code"),
            inverseJoinColumns = @JoinColumn(name = "ks_code")
    )
    private List<Skill> requiredSkills;

    public JobProfile() {

    }

    public JobProfile(String id, String title, String description, Float avgPay) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.avgPay = avgPay;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAvgPay() {
        return avgPay;
    }

    public void setAvgPay(Float avgPay) {
        this.avgPay = avgPay;
    }

    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobProfile)) return false;

        JobProfile that = (JobProfile) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (avgPay != null ? !avgPay.equals(that.avgPay) : that.avgPay != null) return false;
        return requiredSkills != null ? requiredSkills.equals(that.requiredSkills) : that.requiredSkills == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (avgPay != null ? avgPay.hashCode() : 0);
        result = 31 * result + (requiredSkills != null ? requiredSkills.hashCode() : 0);
        return result;
    }
}
