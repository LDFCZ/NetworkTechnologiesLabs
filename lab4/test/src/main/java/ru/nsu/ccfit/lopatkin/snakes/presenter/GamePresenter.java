package ru.nsu.ccfit.lopatkin.snakes.presenter;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.lopatkin.snakes.presenter.event.UserEvent;


public interface GamePresenter {
    void fireEvent(@NotNull UserEvent userEvent);
}
