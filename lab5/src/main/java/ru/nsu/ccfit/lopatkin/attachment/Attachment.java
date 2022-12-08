package ru.nsu.ccfit.lopatkin.attachment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Attachment {
    @NotNull
    private final AttachmentType attachmentType;

    public Attachment(@NotNull AttachmentType attachmentType) {
        this.attachmentType = Objects.requireNonNull(attachmentType, "Attachment type cant be null");
    }

    @NotNull
    public AttachmentType getType() {
        return attachmentType;
    }
}
