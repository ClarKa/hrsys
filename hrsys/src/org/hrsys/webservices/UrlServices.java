package org.hrsys.webservices;

import org.hrsys.constants.ServicePaths;
import org.hrsys.dto.UrlDTO;
import org.hrsys.helpers.MetaAnnotations.IsAuthenticated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ServicePaths.GET_ALL_REST_URLS_PATH)
public class UrlServices {
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @IsAuthenticated
    public UrlDTO getAllRestUrls() {
        return new UrlDTO();
    }
}
