import * as React from "react";
import { HashRouter as Router, Route, Link, Switch } from 'react-router-dom';
import { Hello } from "./Hello";
import {Url} from "./Url";
import {Counter} from "./Counter";
import {GlobalCounter} from "./GlobalCounter";
import {Net} from "./Net";
import {TwoWay} from "./TwoWay";



/** App has one property: a number */
type AppProps = { num: number }

export class App extends React.Component<AppProps> {
    /** The global state for this component is a counter */
    state = { num: 0 };

    /**
     * When the component mounts, we need to set the initial value of its
     * counter
     */
    componentDidMount = () => { this.setState({ num: this.props.num }); }

    /** Get the current value of the counter */
    getNum = () => this.state.num;

    /** Set the counter value */
    setNum = (num: number) => this.setState({ num });

    /** render the component */
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
                        &nbsp;|&nbsp;
                        <Link to="/counter">Counter</Link>
                        &nbsp;|&nbsp;
                        <Link to="/globalcounter">Global Counter</Link>
                        &nbsp;|&nbsp;
                        <Link to="/net">Network</Link>
                        &nbsp;|&nbsp;
                        <Link to="/twoway">Two-Way</Link>
                    </nav>
                    <Switch>
                        <Route exact path="/" component={Hello} />
                        <Route exact path="/hello" component={Hello} />
                        <Route exact path="/hello" render={() => <Hello message={"There"} />} />
                        <Route exact path="/url/:num" component={Url} />
                        <Route exact path="/counter" component={Counter} />
                        <Route exact path="/globalcounter" render={() => <GlobalCounter getNum={this.getNum} setNum={this.setNum} />} />
                        <Route exact path="/net" component={Net} />
                        <Route exact path="/twoway" component={TwoWay} />
                    </Switch>
                    <div>
                        &copy; 2021
                    </div>
                    <div>
                        &copy; 2021 &mdash; The global counter value is {this.state.num}
                    </div>
                </div>
            </Router>
        );
    }
}