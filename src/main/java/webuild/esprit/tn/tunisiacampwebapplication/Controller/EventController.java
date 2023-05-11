package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Event;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IEventServices;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/DetailEvent")
public class EventController {
    IEventServices eventServices;


    //http://localhost:8092/Campi/AUTH/auth/DetailEvent/add-event
    @PostMapping("/add-event")
    public Event addEvent(@RequestBody Event event) {
        return eventServices.addEvent(event);
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailEvent/getAllEvents
    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents() {
        return eventServices.getAllEvents();
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailEvent/FindByIdEvent/{id}
    @GetMapping("/FindByIdEvent/{id}")
    public Event findByIdEvent(@PathVariable(value = "id")Integer IdEvent) {
        return eventServices.findByIdEvent(IdEvent);
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailEvent/EventDelete/{id}
    @DeleteMapping("/EventDelete/{id}")
    public void removeEventByid(@PathVariable(value = "id")Integer IdEvent) {
        eventServices.removeEventByid(IdEvent);
    }
    //http://localhost:8092/Campi/AUTH/auth/DetailEvent/EventUpdate/{id}
    @PutMapping("/EventUpdate/{id}")
    public Event updateEvent(@PathVariable(value = "id")Integer id,@Valid @RequestBody Event event) {
        return eventServices.updateEvent(id,event);
    }


    @PostMapping("/add-Event/{userId}")
    public Event addEventAndAssignUser(@RequestBody Event event, @PathVariable(value = "userId") Integer userId) {
        return eventServices.addEventAndAssignOwner(event, userId);
    }

    @PostMapping("/add-user-to-event/{eventId}/{userId}")
    public Event addUserToEvent(@PathVariable(value = "eventId") Integer idEvent, @PathVariable(value = "userId") Integer idUser) {
        return eventServices.addUserToEvent(idEvent, idUser);
    }
}
