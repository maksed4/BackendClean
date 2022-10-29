package se.ecutbildning.cleanice.entities.dto;

public record CleaningCompletedDTO(long cleaningId, long cleanerId, boolean isCompleted) {
}
