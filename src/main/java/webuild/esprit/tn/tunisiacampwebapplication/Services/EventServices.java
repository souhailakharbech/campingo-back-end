package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Event;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.EventRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EventServices implements IEventServices {

    EventRepo eventRepo;
    Iuser iuser;


    @Override
    public Event addEvent(Event event) {

        return eventRepo.save(event);
    }

    @Override
    public List<Event> getAllEvents() {

        return eventRepo.findAll();
    }

    @Override
    public Event findByIdEvent(Integer IdEvent) {
        return eventRepo.findById(IdEvent).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public void removeEventByid(Integer IdEvent) {

        eventRepo.deleteById(IdEvent);
    }

    @Override
    public Event updateEvent(Integer id, Event event) {
        Event e = eventRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        e.setDateEvent(event.getDateEvent());
        e.setLocation(event.getLocation());
        e.setNumberMaxOfUsers(event.getNumberMaxOfUsers());
        e.setPrix(event.getPrix());
        return eventRepo.save(e);
    }

    @Override
    public Event addEventAndAssignOwner(Event event, Integer idUser) {
        User owner = iuser.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        event.setOwner(owner);
        return eventRepo.save(event);
    }


    /////////////////////////addUserToEvent//////////////////
    @Override
    public Event addUserToEvent(Integer idEvent, Integer idUser) {
        Event event = eventRepo.findById(idEvent)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        User user = iuser.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        event.getUsers().add(user);
        return eventRepo.save(event);
    }
    }



