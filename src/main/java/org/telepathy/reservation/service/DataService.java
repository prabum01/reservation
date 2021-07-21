package org.telepathy.reservation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telepathy.reservation.enums.RoomStatus;
import org.telepathy.reservation.model.Room;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataService {

    @Value( "${number.of.floors}" )
    private Integer floorCount;

    @Value( "${room.numbers}" )
    private String roomNumbers;

    private List<String> roomNumberList;
    private ConcurrentHashMap<Integer, Room> hotelRooms;
    private PriorityBlockingQueue<Room> priorityRooms;

    public DataService(){
        initializeRooms();
    }

    private void initializeRooms(){
        if(floorCount < 1 || (roomNumbers!=null && roomNumbers.length() < 1)){
            throw new IllegalArgumentException("Floor or Room must be at least 1");
        }

        roomNumberList = Arrays.stream(StringUtils.split(roomNumbers, ",")).collect(Collectors.toList());
        hotelRooms = new ConcurrentHashMap<>();
        hotelRooms.put(0, new Room(0, "Lobby", null));
        priorityRooms = new PriorityBlockingQueue<>(floorCount * roomNumberList.size(),
                Comparator.comparing(Room::getRoomId)
        );

        int totalRoomCounter = 1;
        int roomCounter = 0;
        for (int i = 1; i < floorCount + 1; i++) {
            while (roomCounter < roomNumberList.size()) {
                totalRoomCounter++;
                Room room = new Room(totalRoomCounter, (i + roomNumberList.get(roomCounter)), RoomStatus.Vacant);
                hotelRooms.put(totalRoomCounter, room);
                priorityRooms.add(room);
                roomCounter++;
            }
            roomCounter = 0;
            Collections.reverse(roomNumberList);
        }
    }

    public ConcurrentHashMap<Integer, Room> getHotelRooms(){
        return hotelRooms;
    }
}
