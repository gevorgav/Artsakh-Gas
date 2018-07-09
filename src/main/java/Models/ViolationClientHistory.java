package Models;

import java.util.Objects;

public class ViolationClientHistory {
    private Integer id;
    private Integer violationId;
    private Integer clientHistoryId;

    public ViolationClientHistory() {
    }

    public ViolationClientHistory(Integer violationId, Integer clientHistoryId) {
        this.violationId = violationId;
        this.clientHistoryId = clientHistoryId;
    }

    public ViolationClientHistory(Integer id, Integer violationId, Integer clientHistoryId) {
        this.id = id;
        this.violationId = violationId;
        this.clientHistoryId = clientHistoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViolationId() {
        return violationId;
    }

    public void setViolationId(Integer violationId) {
        this.violationId = violationId;
    }

    public Integer getClientHistoryId() {
        return clientHistoryId;
    }

    public void setClientHistoryId(Integer clientHistoryId) {
        this.clientHistoryId = clientHistoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViolationClientHistory that = (ViolationClientHistory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(violationId, that.violationId) &&
                Objects.equals(clientHistoryId, that.clientHistoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, violationId, clientHistoryId);
    }
}
