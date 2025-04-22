package com.angelldca.siga.application.port.in.command.evento;


import com.angelldca.siga.application.port.in.command.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteListEventCommand implements ICommand {

    private List<Long> ids;
}
