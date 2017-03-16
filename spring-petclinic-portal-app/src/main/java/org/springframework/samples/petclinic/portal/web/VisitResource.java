/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.portal.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.visits.service.VisitDetails;
import org.springframework.samples.petclinic.visits.service.VisitService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 * @author Maciej Szarlinski
 */
@RestController
@RequestMapping("/api/visit")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class VisitResource {

    private final VisitService visitService;

    @PostMapping("/owners/*/pets/{petId}/visits")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(
        @Valid @RequestBody VisitDetails visit,
        @PathVariable("petId") int petId) {

//        visit.setPetId(petId);
        log.info("Saving visit {}", visit);
        visitService.save(visit);
    }

    @GetMapping("/owners/*/pets/{petId}/visits")
    public List<VisitDetails> visits(@PathVariable("petId") int petId) {
        return visitService.findByPetId(petId);
    }

}
