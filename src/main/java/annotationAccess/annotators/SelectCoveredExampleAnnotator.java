/**
 * 
 */
package annotationAccess.annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import annotationAccess.util.MiscUtil;
import common.types.POS;
import common.types.Token;

/**
 * Annotator which subiterates with the uimaFIT method 'selectCovered'
 * The method does not require to specify a type priority
 * We assume token and pos annotations occurring at the same spans 
 * the annotator subiterates the token annotations to get the POS annotations
 */
public class SelectCoveredExampleAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		for (Annotation token : JCasUtil.select(aJCas, Token.class)) {
			for (Annotation pos : JCasUtil.selectCovered(aJCas, POS.class, token)){
				MiscUtil.echo(pos);
			}
		}
	}
}