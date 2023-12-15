package com.yepdevelopment.failure.Utils.Android;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A helper class for common navigation functions.
 */
public class Navigator {
    /**
     * This class should not be instantiated.
     */
    private Navigator() {

    }

    /**
     * Replace a viewport with a new fragment.
     *
     * @param fragmentManager the FragmentManager to perform navigation operations on
     * @param to              the fragment to navigate to
     * @param viewportId      the resource ID of the viewport (R.id.xxx)
     * @param tagName         an identifier for this fragment that can later be used by this method to check for duplicate navigation calls
     * @param clearHistory    whether the navigation history should be cleared before navigating to the new fragment
     * @implNote This implementation attempts to check if the given fragment is already being displayed before navigating. If the given fragment is already displayed, the procedure will not navigate.
     */
    public static void navigate(FragmentManager fragmentManager, Fragment to, int viewportId, String tagName, boolean clearHistory) {
        if (fragmentManager == null) {
            System.err.println("fragmentManager was null in navigate() call");
            return;
        }

        if (clearHistory) {
            clearHistory(fragmentManager);
        } else {
            Fragment fragment = fragmentManager.findFragmentByTag(tagName);

            if (fragment != null && fragment.isVisible()) {
                System.out.println("Fragment with %s is already visible. Not navigating...");
                return;
            }
        }

        fragmentManager.beginTransaction().replace(viewportId, to, tagName).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setReorderingAllowed(true).addToBackStack(tagName).commit();
    }

    /**
     * Replace a viewport with a new fragment.
     *
     * @param fragmentManager the FragmentManager to perform navigation operations on
     * @param to              the fragment to navigate to
     * @param viewportId      the resource ID of the viewport (R.id.xxx)
     * @param clearHistory    whether the navigation history should be cleared before navigating to the new fragment
     * @implNote This implementation attempts to navigate without checking if the given fragment is already displayed.
     */
    public static void navigate(FragmentManager fragmentManager, Class<? extends Fragment> to, int viewportId, boolean clearHistory) {
        if (fragmentManager == null) {
            System.err.println("fragmentManager was null in navigate() call");
            return;
        }

        if (clearHistory) clearHistory(fragmentManager);

        fragmentManager.beginTransaction().replace(viewportId, to, null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).setReorderingAllowed(true).addToBackStack(null).commit();
    }

    /**
     * Clear all navigation history.
     *
     * @param fragmentManager the FragmentManager to perform navigation operations on
     */
    public static void clearHistory(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            System.err.println("fragmentManager was null in clearHistory() call");
            return;
        }

        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Check whether this FragmentManager has a Fragment to go back to (as defined by this class).
     * <p/>
     * If you wanted to bypass this check,  you can initiate a transaction on the FragmentManager manually.
     *
     * @param fragmentManager the FragmentManager to perform navigation operations on
     * @return true if the FragmentManager can go back, false otherwise
     */
    public static boolean canGoBack(FragmentManager fragmentManager) {
        if (fragmentManager.getBackStackEntryCount() < 1) {
            System.err.println("The FragmentManager back stack is out of Fragments!");
            return false;
        }

        FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        if (entry.getName() != null && entry.getName().equalsIgnoreCase("root")) {
            System.err.println("The FragmentManager back stack has encountered a Fragment has a root tag, preventing any more back stack pops.");
            return false;
        }

        return true;
    }

    /**
     * Tells the given FragmentManager to navigate back one level.
     *
     * @param fragmentManager the FragmentManager to perform navigation operations on
     */
    public static void goBack(FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            System.err.println("fragmentManager was null in goBack() call");
            return;
        }

        if (!canGoBack(fragmentManager)) return;

        fragmentManager.popBackStack();
    }

}
