package groupwork.web.controllers;

import groupwork.dto.VoiceDTO;
import groupwork.service.api.IVotesService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class UserAnswerController {
    private final IVotesService voteService;

    public UserAnswerController(IVotesService voteService) {
        this.voteService = voteService;

    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveVoice(@RequestBody VoiceDTO voice) {
        voteService.save(voice);
    }
}