import * as React from "react";
import { HashRouter as Router, Route, Link, Switch } from 'react-router-dom';
import { Hello } from "./Hello";
import {Url} from "./Url";

export class App extends React.Component {
    render() {
        return (
            <Router>
                <div>
                    <nav>
                        <Link to="/">Hello (1)</Link>
                        &nbsp;|&nbsp;
                        <Link to="/hello">Hello (2)</Link>
                        &nbsp;|&nbsp;
                        <Link to="/url/1">Url (1)</Link>
                        &nbsp;|&nbsp;
                        <Link to="/url/2">Url (2)</Link>
                    </nav>
                    <Switch>
                        <Route exact path="/" component={Hello} />
                        <Route exact path="/hello" component={Hello} />
                        <Route exact path="/hello" render={() => <Hello message={"There"} />} />
                        <Route path="/url/:num" component={Url} />
                    </Switch>
                    <div>
                        &copy; 2021
                    </div>
                </div>
            </Router>
        );
    }
}