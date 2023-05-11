package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Event;

import java.util.List;

public interface IEventServices {

    Event addEvent(Event event);

    List<Event> getAllEvents();

    Event findByIdEvent(Integer IdEvent);

    void removeEventByid(Integer IdEvent);

    Event updateEvent(Integer id, Event event);

    Event addEventAndAssignOwner(Event event, Integer idUser);


    /////////addUserToEvent////////
    Event addUserToEvent(Integer idEvent, Integer idUser);
}
