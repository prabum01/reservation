package org.telepathy.reservation.enums;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * The enum Room status rules.
 */
public enum RoomStatusRules {
    /**
     * Available room status rules.
     */
    Available(ImmutableList.of(RoomStatus.Occupied)),
    /**
     * Occupied room status rules.
     */
    Occupied(ImmutableList.of(RoomStatus.Vacant)),
    /**
     * Vacant room status rules.
     */
    Vacant(ImmutableList.of(RoomStatus.Available, RoomStatus.Repair)),
    /**
     * Repair room status rules.
     */
    Repair(ImmutableList.of(RoomStatus.Vacant));

    /**
     * Possible status room status rules.
     */
    List<RoomStatus> possibleStatus;
    <E> RoomStatusRules(List<RoomStatus> possibleStatus) {
        this.possibleStatus = possibleStatus;
    }
}
