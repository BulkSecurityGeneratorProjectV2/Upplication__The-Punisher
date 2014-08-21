package com.upplication.thepunisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
class PunishmentController {

    @Autowired
    private PunishmentRepository punishmentRepository;


    @RequestMapping(value = "save-punishment",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Success savePunishment(@Valid @RequestBody PunishmentForm form) {
        Punishment punishment = punishmentRepository.create(form.getTitle(), form.getDescription());
        return new Success(true, punishment.getId(), form.getTitle(), form.getDescription());
    }


    @RequestMapping(value = "create-punishment")
    public String savePunishment() {
        return "thepunisher/create";
    }


    @RequestMapping(value = "list-punishment")
    @ResponseBody
    public List<Punishment> listPunishment() {
        return punishmentRepository.list();
    }


    public static class Success {

        private boolean success;
        private int id;
        private String title;
        private String description;

        public Success(boolean success, int id, String title, String descrption){
            this.success = success;
            this.title = title;
            this.description = descrption;
            this.id = id;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }
    }

}
