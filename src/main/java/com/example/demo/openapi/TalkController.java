package com.example.demo.openapi;

import com.example.demo.openapi.entity.Talk;
import com.example.demo.openapi.exception.ErrorMessage;
import com.example.demo.openapi.service.TalkService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Class to expose endpoints
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
@Path("/talks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class TalkController {

    @Inject
    TalkService talkService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "hello service")
    @Tag(name = "Greetings")
    public String sayHello() {
        return "Hello!!!!!";
    }

    @GET
    @Path("/all")
    @Operation(summary = "See all talks")
    @Tag(name = "Talks")
    public List<Talk> getAllTalks() {
        return talkService.getAllTalks();
    }

    @GET
    @Path("/speaker/{speaker}")
    @Operation(summary = "See all talks filtered by speaker")
    @Tag(name = "Talks")
    @APIResponses(value = {
            @APIResponse(description = "Invalid input", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            ),
            @APIResponse(description = "Talks found", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Talk.class))
            )
    })
    public List<Talk> getTalksBySpeaker(@Parameter(name = "speaker", description = "Speaker name")
                                        @PathParam("speaker") String speaker) {
        return talkService.getTalksBySpeaker(speaker);
    }

    @GET
    @Path("/period")
    @Operation(summary = "See all talks filtered by a range of period")
    @Tag(name = "Talks")
    @APIResponses(value = {
            @APIResponse(description = "Invalid input", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class))
            ),
            @APIResponse(description = "Talks found", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Talk.class))
            )
    })
    public List<Talk> getTalksByPeriod(
            @Parameter(name = "from", description = "This parameter is the period when the talks started. Default value is 201911.")
            @QueryParam("from") @DefaultValue("201911") Integer from,

            @Parameter(name = "to", description = "This parameter is the last period of the first year of the talks. Default value is 202012.")
            @QueryParam("to") @DefaultValue("202012") Integer to) {

        return talkService.getTalksByRangeOfPeriod(from, to);
    }

    @GET
    @Path("/speakers")
    @Operation(summary = "See all speakers")
    @Tag(name = "Speakers")
    @APIResponses(value = {
            @APIResponse(description = "All speakers", responseCode = "200")
    })
    public List<String> getSpeakers() {
        return talkService.getSpeakers();
    }
}
