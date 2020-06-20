import * as React from "react";
import * as ReactDOM from "react-dom";
import "../css/style.css";

const Navigation: React.SFC<{ name: string }> = (props) => {
  React.useEffect(() => { 
    document.title = props.name;
  })

  return (
    <nav className="navbar navbar-light bg-light navbar-expand">
      <a className="navbar-brand" href="#">
        {props.name}
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarColor01"
        aria-controls="navbarColor01"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarColor01">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:8080">
              Producer App
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:8084">
              Alaytics App
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:8500">
              <img src="images/consul.png" />
              Consul
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:3000">
              {" "}
              <img src="images/grafana.png" />
              Grafana
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:9411">
              <img src="images/zipkin.png" />
              Zipkin
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:8081">
              <img src="images/mongo.png" /> Mongo Express
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://localhost:9090">
              <img src="images/prometheus.png" />
              Prometheus
            </a>
          </li>
        </ul>
      </div>
    </nav>
  );
};

interface StructureProps {
  appName: string;
  fluid: boolean;
  children: any;
}
export const StructureComponent: React.SFC<StructureProps> = ({appName,fluid,children}) => {
  return (
    <>
      <Navigation name={appName} />
      <div className={fluid ? "container-fluid" : "container"}>
        {children}
      </div>
    </>
  );
};
