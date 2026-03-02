package br.com.astatine.essentialsSLS.commands;

import com.hypixel.hytale.builtin.teleport.commands.warp.WarpGoCommand;
import com.hypixel.hytale.builtin.teleport.commands.warp.WarpSetCommand;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class WarpCommand extends AbstractCommandCollection {
    public WarpCommand() {
        super("warp", "essentialssls.commands.warp.desc");
        this.addSubCommand(new WarpSetCommand());
        this.addSubCommand(new WarpGoCommand());
        //this.addSubCommand(new WarpRemoveCommand());
    }
}