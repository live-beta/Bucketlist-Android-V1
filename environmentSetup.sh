
#!/bin/bash

# Fix the CircleCI path
function getAndroidSDK {
  export PATH="$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$PATH"

  DEPS="$ANDROID_HOME/installed-dependencies"

  if [ ! -e $DEPS ]; then
    echo y | android update sdk -u -a -t android-24 &&
    echo y | android update sdk -u -a -t platform-tools &&
    echo y | android update sdk -u -a -t build-tools-25.0.2 &&
    echo y | android update sdk -u -a -t "extra-android-m2repository" &&
    echo y | android update sdk -u -a -t "extra-android-support" &&
    echo y | android update sdk -u -a -t "extra-google-m2repository" &&
    echo no | android create avd -n testAVD -f -t android-24 --abi default/armeabi-v7a &&
    touch $DEPS
  fi
}

function copyEnvVarsToGradleProperties {
    GRADLE_PROPERTIES=$HOME"/gradle.properties"
    export GRADLE_PROPERTIES
    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"

    if [ ! -f "$GRADLE_PROPERTIES" ]; then
        echo "Gradle Properties does not exist"

        echo "Creating Gradle Properties file..."
        touch $GRADLE_PROPERTIES

        echo "Writing TEST_API_KEY to gradle.properties..."
        echo "MERCHANT_BASE_URL_SANDBOX=$MERCHANT_BASE_URL_SANDBOX" >> $GRADLE_PROPERTIES
        echo "MERCHANT_BASE_URL_PRODUCTION=$MERCHANT_BASE_URL_PRODUCTION" >> $GRADLE_PROPERTIES
        echo "MERCHANT_CLIENT_KEY_SANDBOX=$MERCHANT_CLIENT_KEY_SANDBOX" >> $GRADLE_PROPERTIES
        echo "MERCHANT_CLIENT_KEY_PRODUCTION=$MERCHANT_CLIENT_KEY_PRODUCTION" >> $GRADLE_PROPERTIES
        echo "MIXPANEL_TOKEN_SANDBOX=$MIXPANEL_TOKEN_SANDBOX" >> $GRADLE_PROPERTIES
        echo "MIXPANEL_TOKEN_PRODUCTION=$MIXPANEL_TOKEN_PRODUCTION" >> $GRADLE_PROPERTIES

    fi
}
function waitForAVD {
  local bootanim=""
  export PATH=$(dirname $(dirname $(which android)))/platform-tools:$PATH
  until [[ "$bootanim" =~ "stopped" ]]; do
    sleep 5
    bootanim=$(adb -e shell getprop init.svc.bootanim 2>&1)
    echo "emulator status=$bootanim"
  done
}
