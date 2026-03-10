/*
 Copyright (c) 2020-2026 Stephen Gold

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 3. Neither the name of the copyright holder nor the names of its
    contributors may be used to endorse or promote products derived from
    this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.stephengold.lbjdroid;

import android.content.Context;
import android.util.Log;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.PlaneCollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.objects.PhysicsBody;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Plane;
import com.jme3.math.Vector3f;

/**
 * Drop a dynamic sphere onto a horizontal surface (non-graphical illustrative
 * example).
 *
 * @author Stephen Gold sgold@sonic.net
 */
final class HelloLibbulletjme implements Runnable {
    // *************************************************************************
    // fields

    /**
     * body that represents a falling ball
     */
    private static PhysicsRigidBody ball;
    /**
     * 3-D space for physics simulation
     */
    private static PhysicsSpace physicsSpace;
    // *************************************************************************
    // constructors

    /**
     * Construct a runnable with the specified context.
     *
     * @param c the environment in which the app is running (not {@code null})
     */
    HelloLibbulletjme(Context c) {
    }
    // *************************************************************************
    // Runnable methods

    /**
     * Simulate a falling sphere and write its coordinates to the activity.
     */
    @Override
    public void run() {
        System.loadLibrary("bulletjme");

        physicsSpace = createSpace();
        populateSpace();

        float timeStep = 0.02f;
        Vector3f location = new Vector3f();
        for (int iteration = 0; iteration < 50; ++iteration) {
            updatePhysics(timeStep);

            ball.getPhysicsLocation(location);
            String textLine = String.format("%s%n", location);
            MainActivity.buffer.append(textLine);
        }

        MainActivity.running = false;
    }
    // *************************************************************************
    // private methods

    /**
     * Create the PhysicsSpace. Invoked once during initialization.
     *
     * @return a new object
     */
    private static PhysicsSpace createSpace() {
        PhysicsSpace result
                = new PhysicsSpace(PhysicsSpace.BroadphaseType.DBVT);
        return result;
    }

    /**
     * Populate the PhysicsSpace with bodies. Invoked once during
     * initialization.
     */
    private static void populateSpace() {
        // Add a static horizontal plane at y=-1:
        float groundY = -1f;
        Plane plane = new Plane(Vector3f.UNIT_Y, groundY);
        CollisionShape planeShape = new PlaneCollisionShape(plane);
        float mass = PhysicsBody.massForStatic;
        PhysicsRigidBody floor = new PhysicsRigidBody(planeShape, mass);
        physicsSpace.addCollisionObject(floor);

        // Add a sphere-shaped, dynamic, rigid body at the origin:
        float radius = 0.3f;
        CollisionShape ballShape = new SphereCollisionShape(radius);
        mass = 1f;
        ball = new PhysicsRigidBody(ballShape, mass);
        physicsSpace.addCollisionObject(ball);
    }

    /**
     * Advance the physics simulation by the specified amount.
     *
     * @param intervalSeconds the amount of time to simulate (in seconds, &ge;0)
     */
    private static void updatePhysics(float intervalSeconds) {
        int maxSteps = 0; // for a single step of the specified duration
        physicsSpace.update(intervalSeconds, maxSteps);
    }
}
