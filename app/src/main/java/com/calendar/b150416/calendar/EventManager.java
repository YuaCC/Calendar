package com.calendar.b150416.calendar;

import java.util.ArrayList;
import java.util.List;

/**
 *事件管理器接口。负责事件的增加，删除，修改，删除。当事件时间到达时,通过NotifyManager实现提醒
 */
public interface EventManager {

    /**
     * 记录事件并且定时准备提醒。同时为该事件生成一个编号,方便主页面以后的删改查。
     * @param e:要添加的事件
     * @return :事件的编号
     */
    int addEvent(Event e);

    /**
     * 移除一个事件
     * @param id 事件的编号
     * @return 如果事件成功移除，返回True，若事件不存在返回False
     */
    boolean removeEvend(int id);

    /**
     * 用一个新的事件代替原来的事件
     * @param id 旧的事件编号
     * @param newEnvent 新的事件
     * @return 如果事件成功替代，返回True，若事件不存在则不添加事件并返回False
     */
    boolean changeEvent(int id,Event newEnvent);

    /**
     * 通过编号获取一个事件的信息。
     * @param id 事件编号
     * @return 事件的信息
     */
    Event getEvent(int id);

    ArrayList<Event> getEventOfDay(int year,int month,int day);
}
