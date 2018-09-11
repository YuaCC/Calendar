package com.calendar.b150416.calendar.Notify;

import com.calendar.b150416.calendar.EventSolve.Event;

/**
 *通知管理器接口。负责提醒用户事件
 */
public interface NotifyManager {
    void notifyEvent(Event e);
}
