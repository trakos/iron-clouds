package pl.trakos.ironClouds.game.enums;

import pl.trakos.lib._;

public enum LossReason
{
    OutOfHealth(_.tr("game.loss.outOfHealth")),
    ZeroMissiles(_.tr("game.loss.zeroMissiles")),
    TooFewMissiles(_.tr("game.loss.tooFewMissiles"));

    public String reason;
    LossReason(String reason)
    {
        this.reason = reason;
    }
}
