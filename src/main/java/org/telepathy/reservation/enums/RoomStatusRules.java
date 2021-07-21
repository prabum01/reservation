package org.telepathy.reservation.enums;

import com.google.common.collect.ImmutableList;

import java.util.List;

public enum RoomStatusRules {
    Available(ImmutableList.of(RoomStatus.Occupied)),
    Occupied(ImmutableList.of(RoomStatus.Vacant)),
    Vacant(ImmutableList.of(RoomStatus.Available, RoomStatus.Repair)),
    Repair(ImmutableList.of(RoomStatus.Vacant));

    List<RoomStatus> possibleStatus;
    <E> RoomStatusRules(List<RoomStatus> possibleStatus) {
        this.possibleStatus = possibleStatus;
    }
}
