package com.example.phlo.myapplication.Model;

import java.util.ArrayList;

/**
 * Created by phlo on 23/10/17.
 */

public class ReservaModel {
    public ReservaModel() {
    }

    private String livronome;
    private String participantesnome;

    public ReservaModel(String livronome, String participantesnome) {
        this.livronome = livronome;
        this.participantesnome = participantesnome;
    }

    public String getLivronome() {

        return livronome;
    }

    public void setLivronome(String livronome) {
        this.livronome = livronome;
    }

    public String getParticipantesnome() {
        return participantesnome;
    }

    public void setParticipantesnome(String participantesnome) {
        this.participantesnome = participantesnome;
    }
}
