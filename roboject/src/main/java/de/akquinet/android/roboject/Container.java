/*

This file is part of Roboject

Copyright (c) 2010-2011 akquinet A.G.

Contact:  http://www.akquinet.de/en

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.akquinet.de/en.

*/
package de.akquinet.android.roboject;

import java.util.List;

import android.content.Context;
import de.akquinet.android.roboject.injectors.Injector;
import de.akquinet.android.roboject.injectors.Injector.InjectorState;


public class Container
{
    public enum LifeCyclePhase
    {
        CREATE, RESUME, STOP, SERVICES_CONNECTED, READY;
    }

    private LifeCyclePhase currentPhase;

    private List<Injector> injectors;

    private Object managed;

    public Container(Context context, Object managed, Class<?> clazz) throws RobojectException {
        this.managed = managed;
        this.currentPhase = LifeCyclePhase.CREATE;
        this.injectors = RobojectConfiguration.getDefaultInjectors(managed);

        for (Injector injector : this.injectors) {
            injector.configure(context, this, managed, clazz);
        }

        for (Injector injector : this.injectors) {
            if (injector.isValid()) {
                injector.start(context, this, managed);
            }
        }
    }

    public void update() {
        if (this.currentPhase == LifeCyclePhase.READY) {
            return;
        }

        if (allInjectorsReady()) {
            invokeReadyPhase();
        }
    }
    
    protected void invokeSetContentView() {
        for (Injector injector : this.injectors) {
            injector.onSetContentView();
        }
    }

    protected void invokeCreatePhase() {
        this.currentPhase = LifeCyclePhase.CREATE;

        for (Injector injector : this.injectors) {
            injector.onCreate();
        }
    }

    protected void invokeResumePhase() {
        this.currentPhase = LifeCyclePhase.RESUME;

        for (Injector injector : this.injectors) {
            injector.onResume();
        }
    }

    protected void invokeStopPhase() {
        this.currentPhase = LifeCyclePhase.STOP;

        for (Injector injector : this.injectors) {
            injector.onStop();
        }
    }

    protected void invokeReadyPhase() {
        this.currentPhase = LifeCyclePhase.READY;
        if (managed instanceof RobojectLifecycle) {
            ((RobojectLifecycle) managed).onReady();
        }
    }

    private boolean allInjectorsReady() {
        for (Injector injector : injectors) {
            if (!InjectorState.READY.equals(injector.getState())) {
                return false;
            }
        }

        return true;
    }

    public LifeCyclePhase getCurrentLifecyclePhase() {
        return this.currentPhase;
    }
}
