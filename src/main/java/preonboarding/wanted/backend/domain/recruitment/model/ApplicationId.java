package preonboarding.wanted.backend.domain.recruitment.model;

import java.io.Serial;
import java.io.Serializable;

public class ApplicationId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long recruitmentId;
    private Long accountId;
}
